package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void shouldNotReturnPedidoWhenGetPedidoById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
               // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnNotFoundWhenAddItemsToNonExistingPedido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/pedidos/{id}/add-items", 999)
                        .contentType("application/json")
                        .content("[{\"id\":1,\"name\":\"Item1\",\"price\":10.0}]"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundWhenDeleteItemFromNonExistingPedido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/pedidos/{id}/delete-item/{idItem}", 999, 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundWhenCheckoutFails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/pedidos/{id}/checkout", 999)
                        .param("collector", "1")
                        .param("pos", "pos"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }




}
