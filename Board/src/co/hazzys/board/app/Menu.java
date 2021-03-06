package co.hazzys.board.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.hazzys.board.service.BoardService;
import co.hazzys.board.serviceImpl.BoardServiceImpl;
import co.hazzys.board.vo.BoardVO;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private BoardService dao = new BoardServiceImpl();

	public void run() {
		mainMenu();
		sc.close();
	}

	private void mainMenu() {
		int Num;
		boolean run = false;
		do {
			boardList();
			System.out.println();
			menuTitle();
			switch (Num = sc.nextInt()) {
			case 1:
				boardSelect();
				break;
			case 2:
				boardInsert();
				break;
			case 3:
				boardUpdate();
				break;
			case 4:
				boardDelete();
				break;
			case 5:
				run = true;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		} while (!run);
	}// end of MM

	private void boardList() {
		List<BoardVO> boards = new ArrayList<BoardVO>();
		boards = dao.selectList();
		System.out.println("             << 게시판 목록 >>");
		System.out.printf("%-3s %-6s %-10s %-10s %-10s %n",
				"|번호","|작성자","|제목","|작성일자","|조회수");
		for (BoardVO board : boards) {
			board.toString();
		}
	}// end of BL

	private void menuTitle() {
		System.out.println("◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼");
		System.out.println("               << 게시판 >>");
		System.out.println("                1.글 조회");
		System.out.println("                2.글 쓰기");
		System.out.println("                3.글 수정");
		System.out.println("                4.글 삭제");
		System.out.println("               5.시스템 종료");
		System.out.println("◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼◻◼");
		System.out.println("\n원하는 메뉴 번호를 입력하세요.");
	}// end of MT

	private void boardSelect() {
		BoardVO vo = new BoardVO();
		System.out.println("조회할 글 번호를 입력하세요.");
		vo.setBoardId(sc.next());
		sc.nextLine();
		vo = dao.boardSelect(vo);
		System.out.println(vo.getSubject());
	}// end of BS

	private void boardInsert() {
		BoardVO vo = new BoardVO();
		System.out.println("작성할 글 번호를 입력하세요.");
		vo.setBoardId(sc.next());
		sc.nextLine();
		System.out.println("작성자를 입력하세요.");
		vo.setWriter(sc.nextLine());
		System.out.println("제목을 입력하세요.");
		vo.setTitle(sc.nextLine());
		System.out.println("내용을 입력하세요.");
		vo.setSubject(sc.nextLine());
		
		int n = dao.boardInsert(vo);
		if (n != 0)
			System.out.println("게시물 작성에 성공하였습니다.");
		else
			System.out.println("게시물 작성에 실패하였습니다.");
		
	}// end of BI
	
	private void boardUpdate() {
		BoardVO vo = new BoardVO();
		System.out.println("수정할 글 번호를 입력하세요.");
		vo.setBoardId(sc.next());
		sc.nextLine();
		System.out.println("제목을 입력하세요.");
		vo.setTitle(sc.nextLine());
		System.out.println("내용을 입력하세요.");
		vo.setSubject(sc.nextLine());
		
		int n = dao.boardUpdate(vo);
		if (n != 0)
			System.out.println("게시물 수정에 성공하였습니다.");
		else
			System.out.println("게시물 수정에 실패하였습니다.");
		
	}//end of BU

	private void boardDelete() {
		BoardVO vo = new BoardVO();
		System.out.println("삭제할 글 번호를 입력하세요.");
		vo.setBoardId(sc.next());
		sc.nextLine();
		
		int n = dao.boardDelete(vo);
		if (n != 0)
			System.out.println("게시물 삭제에 성공하였습니다.");
		else
			System.out.println("게시물 삭제에 실패하였습니다.");
		
	}// end of BD

}