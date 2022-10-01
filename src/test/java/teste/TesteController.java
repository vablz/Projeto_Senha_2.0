/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import controller.AtendimentoController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.AtendimentoModel;
import org.junit.Test;

/**
 *
 * @author Lucio
 */
public class TesteController {

    @Test
    public void testeControllerInsert() throws SQLException {
        AtendimentoModel atendimentoModel = new AtendimentoModel();

        atendimentoModel.setNome("TESTE22 " + new Date());
        atendimentoModel.setData(new Date());

        AtendimentoController atendimentoController = new AtendimentoController();

        atendimentoController.save(atendimentoModel);

        Integer cod = atendimentoModel.getId();

        if (cod != null) {
            System.out.println("Registro inserido" + atendimentoModel.toString());
        } else {
            System.err.println("Falha ao inserir o registro");
        }
    }

    @Test
    public void testeGetAll() throws SQLException {
        AtendimentoController atendimentoController = new AtendimentoController();
        List<AtendimentoModel> atendimentoModels = atendimentoController.getAll();
        for (AtendimentoModel atendimentoModel : atendimentoModels) {
            System.out.println("OK" + atendimentoModel.toString());
        }
    }

    @Test
    public void testeGetFirst() throws SQLException {
        AtendimentoController atendimentoController = new AtendimentoController();
        AtendimentoModel atendimentoModels = atendimentoController.getFirst();
        System.out.println("OK " + atendimentoModels.toString());
    }

    @Test
    public void testeGetNextList() throws SQLException {
        AtendimentoController atendimentoController = new AtendimentoController();
        List<AtendimentoModel> atendimentoModels = atendimentoController.getNextList();
        for (AtendimentoModel atendimentoModel : atendimentoModels) {
            System.out.println("OK " + atendimentoModel.toString());
        }
    }

    @Test
    public void testeGetChamadoList() throws SQLException {
        AtendimentoController atendimentoController = new AtendimentoController();
        List<AtendimentoModel> atendimentoModels = atendimentoController.getChamadosList();
        for (AtendimentoModel atendimentoModel : atendimentoModels) {
            System.out.println("OK" + atendimentoModel.toString());
        }

    }

    @Test
    public void testeGetChamado() throws SQLException {
        AtendimentoController atendimentoController = new AtendimentoController();
        AtendimentoModel atendimentoModels = atendimentoController.getChamado();
        System.out.println("OK" + atendimentoModels.toString());
    }

    @Test
    public void testeUpdateJaAtendido() throws SQLException {
        AtendimentoModel atendimentoModels = new AtendimentoModel();
        AtendimentoController atendimentoController = new AtendimentoController();
        try {
            atendimentoController.updateJaAtendido();
            System.out.println("FUNCIONOU " + atendimentoModels);
        } catch (Exception e) {
            System.out.println("NÃO FUNCIONOU " + atendimentoModels);
        }
    }

    @Test
    public void testeUpdate() throws SQLException {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        atendimentoModel.setNome("TESTE NOME TROCADO");
        atendimentoModel.setData(new Date());
        AtendimentoController atendimentoController = new AtendimentoController();
        atendimentoController.save(atendimentoModel);
        atendimentoModel.setStatus(1);
        atendimentoModel.setAtendimento(new Date());
        atendimentoController.update(atendimentoModel);
    }

}