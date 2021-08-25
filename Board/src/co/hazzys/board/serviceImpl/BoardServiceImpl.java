package co.hazzys.board.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hazzys.board.dao.DAO;
import co.hazzys.board.service.BoardService;
import co.hazzys.board.vo.BoardVO;

public class BoardServiceImpl extends DAO implements BoardService {
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> selectList() { // 전체리스트
		List<BoardVO> posts = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select boardid,writer,title,enterdate,hit from board";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardId(rs.getString("boardId"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				posts.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}//end of SL

	@Override
	public BoardVO boardSelect(BoardVO vo) { // 게시물조회
		String sql = "select subject from board where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setSubject(rs.getString("subject"));
				hitUpdate(vo.getBoardId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}//end of BS

	private void hitUpdate(String boardId) {
		String sql = "update board set hit = hit + 1 where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardId);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//end of HU

	@Override
	public int boardInsert(BoardVO vo) { //
		int n = 0;
		String sql = "insert into board(boardid,writer,title,subject) values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			psmt.setString(2, vo.getWriter());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getSubject());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}//end of BI
	
	@Override
	public int boardUpdate(BoardVO vo) {
		int n = 0;
		String sql = "update board set title = ?, subject = ? where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getSubject());
			psmt.setString(3, vo.getBoardId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}//end of BU
	
	@Override
	public int boardDelete(BoardVO vo) {
		int n = 0;
		String sql = "delete from board where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}//end of BD
}//end of class
