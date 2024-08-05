package wanted.board.job.service;

import java.util.List;
import java.util.Map;

public interface jobService {

	//채용 공고 등록
	void jobBoardInsert(Map<String, Object> map) throws Exception;

	// 채용 공고 상세페이지
	Map<String, Object> jobBoardDetail(Map<String, Object> map) throws Exception;

	// 해당 회사가 올린 다른 채용공고
	List<Map<String, Object>> listByCompany(Map<String, Object> map) throws Exception;
	
	// 채용 공고 수정
	void jobBoardUpdate(Map<String, Object> map) throws Exception;

	// 채용 공고 삭제
	void jobBoardDelete(Map<String, Object> map) throws Exception;

	// 채용 공고 리스트
	List<Map<String, Object>> jobBoardList(Map<String, Object> map) throws Exception;

	// 채용 공고 리스트 검색
	List<Map<String, Object>> jobBoardSearchList(Map<String, Object> map) throws Exception;

	// 총 채용 공고 수
	int jobBoardTotal() throws Exception;

}
