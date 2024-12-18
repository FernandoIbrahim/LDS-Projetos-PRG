
package PRG.CarRent.Model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import PRG.CarRent.Util.Enums.StatusPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpresaModel extends Agente{
    
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "qtde_funcionarios", nullable = false)
    private int qtdeFuncionarios;   

    @OneToMany(mappedBy = "empresa")
    private List<PedidoModel> pedidos;

    @JsonIgnore
    public List<PedidoModel> analisarPedidos(){
        return this.pedidos;
    }

    public void modificarPedido(PedidoModel pedido){
        this.pedidos.add(pedido);
    }

    //  public boolean avaliarPedido(PedidoModel pedido, boolean status){
    //     if (pedido.getStatusPedido() == StatusPedido.APROVADO || pedido.getStatusPedido() == StatusPedido.REPROVADO){
    //         return false;
            
    //     }
    //     if (status) {
    //         pedido.setStatusPedido(StatusPedido.APROVADO);
    //     }
    //     else{
    //         pedido.setStatusPedido(StatusPedido.REPROVADO);
    //     }

    //     return true;
    // }


}
