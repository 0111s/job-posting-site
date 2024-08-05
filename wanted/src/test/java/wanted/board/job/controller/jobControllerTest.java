package wanted.board.job.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class jobControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.session = new MockHttpSession();
    }
    
    
    // 1. 채용공고를 등록합니다.
    @Test
    public void testJobBoardInsertForm() throws Exception {
        mockMvc.perform(get("/jobBoard/insertForm"))
               .andExpect(status().isOk())
               .andExpect(view().name("/job/insert"));
    }

    @Test
    public void testJobBoardInsert() throws Exception {
        mockMvc.perform(post("/jobBoard/insert")
               .param("TITLE", "Test Job")
               .param("COMPANY", "Test Company")
               .param("COUNTRY", "Test Country")
               .param("REGION", "Test Region")
               .param("POSITION", "Test Position")
               .param("EXPERIENCE", "Test Experience")
               .param("COMP", "50000")
               .param("SKILL", "Test Skill")
               .param("DETAIL", "Test Detail"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/jobBoard/list.com"))
               .andExpect(flash().attributeExists("success"));
    }

    
    // 2. 채용공고를 수정합니다.    
    @Test
    public void testJobBoardUpdateForm() throws Exception {
        mockMvc.perform(get("/jobBoard/updateForm")
               .param("NOTICE_ID", "10000"))
               .andExpect(status().isOk())
               .andExpect(view().name("/job/updateForm"))
               .andExpect(model().attributeExists("map"));
    }
   
    @Test
    public void testJobBoardUpdate() throws Exception {
        mockMvc.perform(post("/jobBoard/update")
               .param("NOTICE_ID", "10000")
               .param("TITLE", "Updated Job")
               .param("COMPANY", "Updated Company")
               .param("COUNTRY", "Updated Country")
               .param("REGION", "Updated Region")
               .param("POSITION", "Updated Position")
               .param("EXPERIENCE", "Updated Experience")
               .param("COMP", "60000")
               .param("SKILL", "Updated Skill")
               .param("DETAIL", "Updated Detail"))
               .andExpect(status().is3xxRedirection())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrlPattern("/jobBoard/list.com*"));
    }

    
	// 3. 채용공고를 삭제합니다.   
    @Test
    public void testJobBoardDelete() throws Exception {
        mockMvc.perform(post("/jobBoard/delete")
               .param("NOTICE_ID", "10000"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/jobBoard/list.com"))
               .andExpect(flash().attributeExists("warning"));
    }

    
	// 4-1. 채용공고 목록을 가져옵니다.   
    @Test
    public void testJobBoardList() throws Exception {
        mockMvc.perform(get("/jobBoard/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/job/list"))
               .andExpect(model().attributeExists("list"))
               .andExpect(model().attributeExists("TOTAL"));
    }

    
	// 4-2. 채용공고 검색 기능 구현   
	@Test
	public void testJobBoardListSearch() throws Exception {
	    mockMvc.perform(get("/jobBoard/list/search")
	           .param("keyword", "test")
	           .param("page", "1")
	           .param("category", ""))
	           .andExpect(status().isOk())
	           .andExpect(view().name("/job/search"))
	           .andExpect(model().attributeExists("boardSearchList"))
	           .andExpect(model().attributeExists("keyword"))
	           .andExpect(model().attributeExists("currentPage"))
	           .andExpect(model().attributeExists("category"));
	}

	
	// 5. 채용 상세 페이지를 가져옵니다.    
    @Test
    public void testJobBoardDetail() throws Exception {
        mockMvc.perform(get("/jobBoard/detail/{NOTICE_ID}", 10000)
	           .param("COMPANY", "Test Company"))
	           .andExpect(status().isOk())
	           .andExpect(view().name("/job/detail"))
	           .andExpect(model().attributeExists("map"))
	           .andExpect(model().attributeExists("listByCompany"));
    }

}
