package statusmgr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DetailedServerStatusControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamDetailsRequestShouldReturnError() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")).andExpect(status().is4xxClientError()).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void paramOperationsDetailsRequest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally"));
    }

    @Test
    public void paramOperationsExtensionsDetailsRequest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations,extensions"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, "+
                        "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));
    }

    @Test
    public void paramOperationsExtensionsMemoryDetailsRequest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations,extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is "+
                        "using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is Running low"));
    }

    @Test
    public void paramOperationsExtensionsMemoryDetailsRequestSpecifiedName() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations,extensions,memory")
                .param("name", "Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, " +
                        "and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is Running low"));
    }

    @Test
    public void paramOperationsMemoryDetailsRequestSpecifiedName() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("name", "Noach")
                .param("details", "operations,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, "
                        +"and its memory is Running low"));
    }

    //4 more test cases

    @Test
    public void paramExtensionsMemoryDetailsRequestSpecifiedNameLast() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed?details=extensions,memory&name=Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions "+
                        "- [Hypervisor, Kubernetes, RAID-6], and its memory is Running low"));
    }

    @Test
    public void paramExtensionsMemoryDetailsRequestSpecifiedNameFirst() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed?name=Noach&details=extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions" +
                        " - [Hypervisor, Kubernetes, RAID-6], and its memory is Running low"));
    }

    @Test
    public void paramOperationsMemoryExtensionsMemory() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory,operations,extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous")).
                andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is Running low," +
                        " and is operating normally, and is using these extensions - [Hypervisor, " +
                        "Kubernetes, RAID-6], and its memory is Running low"));
    }

//    @Test
//    public void invalidDetailParamInDetailsRequest() throws Exception
//    {
//        this.mockMvc.perform(get("/server/status/detailed")
//                .param("details", "memory,operations,junkERROR"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach")).
//                andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and its memory is Running low"));
//    }
}
