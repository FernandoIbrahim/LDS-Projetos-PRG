package PRG.CarRent.Controller;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.ClienteModel;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<ClienteModel> getAllClientes() {
        return entityManager.createQuery("SELECT c FROM ClienteModel c", ClienteModel.class).getResultList();
    }

    @Operation(description = "Busca um cliente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long id) {
        ClienteModel cliente = entityManager.find(ClienteModel.class, id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @Operation(description = "Cria um novo cliente")
    @PostMapping
    @Transactional
    public ResponseEntity<ClienteModel> createCliente(@RequestBody ClienteModel cliente) {
        cliente.setCliente_id(null);
        entityManager.persist(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Operation(description = "Atualiza um cliente existente")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetails) {
        ClienteModel cliente = entityManager.find(ClienteModel.class, id);

        if (cliente != null) {
            cliente.setCpf(clienteDetails.getCpf());
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setProfissao(clienteDetails.getProfissao());
            cliente.setRg(clienteDetails.getRg());
            cliente.setRua(clienteDetails.getRua());
            cliente.setNumero(clienteDetails.getNumero());
            cliente.setBairro(clienteDetails.getBairro());
            cliente.setCidade(clienteDetails.getCidade());
            cliente.setEstado(clienteDetails.getEstado());
            cliente.setPais(clienteDetails.getPais());
            cliente.setCep(clienteDetails.getCep());
            cliente.setRendimentos(clienteDetails.getRendimentos());
            cliente.setEmpregadores(clienteDetails.getEmpregadores());

            entityManager.merge(cliente);
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(description = "Deleta um cliente existente")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        ClienteModel cliente = entityManager.find(ClienteModel.class, id);

        if (cliente != null) {
            entityManager.remove(cliente);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}