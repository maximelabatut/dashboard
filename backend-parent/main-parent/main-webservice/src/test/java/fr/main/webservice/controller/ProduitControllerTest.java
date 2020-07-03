package fr.main.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.main.service.ProduitService;
import fr.main.service.dto.ProduitDTO;
import fr.main.service.exception.ResourceNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProduitControllerTest extends Mockito {

    private static final String PATH = "/produit";
    private MockMvc mockMvc;

    @InjectMocks
    private final ProduitController produitController = new ProduitController();

    @Mock
    private ProduitService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(produitController).build();
    }

    private ProduitDTO createProduit(Long index){
        ProduitDTO produit = new ProduitDTO();
        produit.setId(index);
        produit.setLibelle("Libelle "+index);
        produit.setReference("Reference "+index);
        return produit;
    }

    private List<ProduitDTO> createListeProduit(){
        List<ProduitDTO> produits = new ArrayList<>();
        for(long i = 1 ; i <= 5 ; i++) {
            produits.add(createProduit(i));
        }
        return produits;
    }

    @Test
    public void findAllTest() throws Exception {
        Mockito.when(this.service.findAll()).thenReturn(createListeProduit());
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(PATH + "/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].libelle").value("Libelle 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reference").value("Reference 1"))
        ;
    }

    @Test
    public void saveTest() throws Exception {
        ProduitDTO produitDTO = createProduit(1L);
        Mockito.when(this.service.save(any())).thenReturn(produitDTO);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(PATH + "/")
                        .content(new ObjectMapper().writeValueAsBytes(produitDTO))
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("Libelle 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reference").value("Reference 1"));
    }

    @Test
    public void deleteByIdOKTest() throws Exception {
        Mockito.when(this.service.findById(anyLong())).thenReturn(createProduit(1L));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteByIdKOTest() throws Exception {
        Mockito.doThrow(ResourceNotFoundException.class).when(this.service).deleteById(anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateTest() throws Exception {
        ProduitDTO produitDTO = createProduit(1L);
        Mockito.when(this.service.save(any())).thenReturn(produitDTO);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(PATH + "/1")
                        .content(new ObjectMapper().writeValueAsBytes(produitDTO))
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("Libelle 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reference").value("Reference 1"));
    }

    @Test
    public void findByIdTest() throws Exception {
        ProduitDTO produitDTO = createProduit(1L);
        Mockito.when(this.service.findById(anyLong())).thenReturn(produitDTO);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("Libelle 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reference").value("Reference 1"));
    }

}
