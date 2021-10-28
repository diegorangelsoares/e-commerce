package br.com.diego.service;

import br.com.diego.exception.PedidoNotFoundException;
import br.com.diego.model.Cliente;
import br.com.diego.model.Pedido;
import br.com.diego.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private BookPedidoService bookPedidoService;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido finById (long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(String.format(
                "Pedido not found by ID [%s]!", id)));
    }

    public List<Pedido> listAll(){
        return pedidoRepository.findAll();
    }

    public Pedido salvarNovo(Pedido pedido){

        if (pedido.getId() != 0){
            //Verificar se esta sendo enviado um pedido com numero inexistente
            pedidoRepository.findById(pedido.getId()).orElseThrow(() -> new PedidoNotFoundException(String.format(
                    "Não existe pedido saldo para alterar com esse ID [%s]!", pedido.getId())));
        }

        Cliente clientePedido = pedido.getCliente();
        Cliente clienteExistente = clienteService.buscarClientePorCPF(pedido.getCliente().getCpf());
        if (clienteExistente!= null){
            clientePedido = clienteExistente;
        }
        clientePedido.setEndereco(enderecoService.SalvarNovo(pedido.getCliente().getEndereco()));;
        pedido.setCliente(clienteService.salvarCliente(clientePedido));
        pedido.setBooks(bookPedidoService.saveAll(pedido.getBooks()));

        return pedidoRepository.save(pedido);
    }

//    public Pedido parse (PedidoRequest pedidoRequest){
//        Optional<Pedido> pedido = pedidoRepository.findById(pedidoRequest.getId());
//        Pedido newPedido = new Pedido();
//        if (pedido.isEmpty()){
//            List<BookPedido> book = new ArrayList<>();
//            for ()
//
//        }
//
//
//        return pedido;
//    }

}
