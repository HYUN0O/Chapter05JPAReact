package user.bean;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserPaging {
	public int currentPage; //현재페이지
	public int pageBlock;//[이전][1][2][3][다음]
	public int pageSize; //1페이지당 3개씩
	public int totalA; //총글수
	public  StringBuffer pagingHTML;
	//StringBuffer 문자열을 처리해주는 [편집이 가능함] String은 편집이 불가함
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + pageSize - 1) / pageSize; //총 페이지 수
		
		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1; //ex) 5 - 1 / 3 * 3 + 1
		int endPage = startPage + pageBlock - 1; //ex)4 + 3 - 1
		if(endPage > totalP) endPage = totalP;
		
		if(startPage != 1) {
			pagingHTML.append("<span id='paging' onclick='userPaging(" + (startPage - 1) + ")'>이전</span>"); //span 태크는 width, height 불가 
		}	
		for(int i = startPage; i <= endPage; i++) {
			if( i == currentPage) {
				pagingHTML.append("<span id='currentPaging' onclick='userPaging(" + i + ")'>" + i + "</span>");
			} else {
				pagingHTML.append("<span id='paging' onclick='userPaging(" + i + ")'>" + i + "</span>");
			}
		} //for
		if(endPage < totalP) {
			pagingHTML.append("<span id='paging' onclick='userPaging(" + (endPage + 1) + ")'>다음</span>");
		}
	} //makePagingHTML()
}
