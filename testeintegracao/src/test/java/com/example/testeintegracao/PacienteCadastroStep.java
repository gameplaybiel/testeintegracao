package com.example.testeintegracao;

import com.example.testeintegracao.entity.Paciente;
import com.example.testeintegracao.repository.PacienteRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PacienteCadastroStep extends StepDefsDefault{

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PacienteRepository pacienteRepository;

    private Paciente pacienteRequest;
    private ResponseEntity<Paciente> response;

    @Dado("que eu possuo os seguintes dados de paciente")
    public void queEuPossuoOsSeguintesDadosDePaciente(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        pacienteRequest = new Paciente();
        pacienteRequest.setNome(data.get("nome"));
        pacienteRequest.setCpf(data.get("cpf"));
        pacienteRequest.setEmail(data.get("email"));
    }

    @Quando("eu faço uma requisição POST para cadastrar o paciente")
    public void euFacoUmaRequisicaoPOSTParaCadastrarOPaciente() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");

        HttpEntity<Paciente> request = new HttpEntity<>(pacienteRequest, headers);
        response = testRestTemplate.exchange(
                new URI("/pacientes"),
                HttpMethod.POST,
                request,
                Paciente.class
        );
    }

    @Então("a resposta deve ter o status code {int}")
    public void aRespostaDeveTerOStatusPaciente(int statusCode){
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @E("a resposta deve conter o nome {string}")
    public void aRespostaDeveConterONome(String nomeEsperado){
        assertThat(response.getBody().getNome()).isEqualTo(nomeEsperado);
    }

    @E("o paciente deve estar salvo no banco de dados")
    public void oPacienteDeveEstarSalvoNoBancoDeDados(){
        Optional<Paciente> pacienteSalvo = pacienteRepository.findAll().stream()
                .filter(p -> p.getCpf().equals(pacienteRequest.getCpf())).findFirst();
        assertThat(pacienteSalvo.isPresent());
        assertThat(pacienteSalvo.get().getNome()).isEqualTo(pacienteRequest.getNome());
    }
}
