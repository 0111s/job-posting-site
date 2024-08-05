package wanted.board.job.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import wanted.board.common.dao.AbstractDAO;

@Repository("jobDAO")
public class jobDAO extends AbstractDAO {

	// 채용 공고 등록
	public void jobBoardInsert(Map<String, Object> map) throws Exception {
		insert("job.jobBoardInsert", map);
	}

	// 채용 공고 상세페이지
	@SuppressWarnings("unchecked")
	public Map<String, Object> jobBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>)selectOne("job.jobBoardDetail", map);
	}
		
	// 해당 회사가 올린 다른 채용공고
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listByCompany(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("job.listByCompany", map);
	}
	
	// 채용 공고 수정
	public void jobBoardUpdate(Map<String, Object> map) throws Exception {
		update("job.jobBoardUpdate", map);
	}
	
	// 채용 공고 삭제
	public void jobBoardDelete(Map<String, Object> map) throws Exception {
		update("job.jobBoardDelete", map);
	}
	
	// 채용 공고 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> jobBoardList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)selectList("job.jobBoardList", map);
	}

	// 채용 공고 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> jobBoardSearchList(Map<String, Object> map) throws Exception {
	    return (List<Map<String, Object>>) selectPagingList("job.jobBoardSearchList", map);
	}

	// 총 채용 공고 수
	public int jobBoardTotal() throws Exception {
		return (Integer) selectOne("job.JobBoardTotal");
	}
	
}
