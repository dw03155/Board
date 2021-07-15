package co.hazzys.board.service;

import java.util.List;

import co.hazzys.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> selectList();
	BoardVO boardSelect(BoardVO vo);
	int boardInsert(BoardVO vo);
//	int boardUpdate(BoardVO vo);
	int boardDelete(BoardVO vo);
}
