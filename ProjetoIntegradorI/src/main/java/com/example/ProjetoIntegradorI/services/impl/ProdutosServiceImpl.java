package com.example.ProjetoIntegradorI.services.impl;

import com.example.ProjetoIntegradorI.models.CategoriaModel;
import com.example.ProjetoIntegradorI.models.CidadesModel;
import com.example.ProjetoIntegradorI.models.ProdutosModel;
import com.example.ProjetoIntegradorI.repositories.CategoriaRepository;
import com.example.ProjetoIntegradorI.repositories.CidadesRepository;
import com.example.ProjetoIntegradorI.repositories.ProdutosRepository;
import com.example.ProjetoIntegradorI.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutosServiceImpl implements IBookingService<ProdutosModel> {

    private final ProdutosRepository produtosRepository;


    public ProdutosServiceImpl(ProdutosRepository produtosRepository) { this.produtosRepository = produtosRepository; }

    @Override
    public ProdutosModel salvar(ProdutosModel produtosModel) {
        if(produtosModel != null){
            return produtosRepository.save(produtosModel);
        }
        return new ProdutosModel();
    }

    @Override
    public String alterar(ProdutosModel produtosModel) {
        if(produtosModel != null && produtosRepository.findById(produtosModel.getId()).isPresent()){
            produtosRepository.saveAndFlush(produtosModel);
            return "Produto alterado com sucesso!";
        }
        return "Não foi possível alterar o produto.";
    }

    @Override
    public List<ProdutosModel> buscarTodos() throws SQLException {
        return produtosRepository.findAll();
    }

    @Override
    public Optional<ProdutosModel> buscarPorId(Long id) throws SQLException {
        return produtosRepository.findById(id);
    }

    @Override
    public boolean excluir(Long id) throws SQLException {
        if(produtosRepository.findById(id).isPresent()){
            produtosRepository.deleteById(id);
            return true;
        }
        return false;
    }

//alex
    public List<ProdutosModel> findByCategoriaModel(CategoriaModel categoria){
        return produtosRepository.findByCategoriaModel(categoria);

    }

    public List<ProdutosModel> findByCidadesModel(CidadesModel cidade){
        return produtosRepository.findByCidadesModel(cidade);
    }


}
