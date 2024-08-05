package wanted.board.job.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wanted.board.job.dao.jobDAO;

@Service("jobService")
public class jobServiceImpl implements jobService {
	
	@Resource(name = "jobDAO")
	private jobDAO jobDAO;
	

	// 채용 공고 등록
	@Override
	public void jobBoardInsert(Map<String, Object> map) throws Exception {
		jobDAO.jobBoardInsert(map);
	}

	
	// 채용 공고 상세페이지
	@Override
	public Map<String, Object> jobBoardDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> jobMap = jobDAO.jobBoardDetail(map);
		return jobMap;
	}
	
	
	// 해당 회사가 올린 다른 채용공고
	@Override
	public List<Map<String, Object>> listByCompany(Map<String, Object> map) throws Exception {
	    return jobDAO.listByCompany(map);
	}
	
	
	// 채용 공고 수정
	@Override
	public void jobBoardUpdate(Map<String, Object> map) throws Exception {
		jobDAO.jobBoardUpdate(map);
	}

	
	// 채용 공고 삭제
	@Override
	public void jobBoardDelete(Map<String, Object> map) throws Exception {
		jobDAO.jobBoardDelete(map);
	}

	
	// 채용 공고 리스트
	@Override
	public List<Map<String, Object>> jobBoardList(Map<String, Object> map) throws Exception {
		return jobDAO.jobBoardList(map);
	}

	
	// 채용 공고 검색
	@Override
	public List<Map<String, Object>> jobBoardSearchList(Map<String, Object> map) throws Exception {
	    return jobDAO.jobBoardSearchList(map);
	}


	// 총 채용 공고 수
	@Override
	public int jobBoardTotal() throws Exception {
		return jobDAO.jobBoardTotal();
	}

}
