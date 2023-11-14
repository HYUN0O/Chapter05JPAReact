package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserUploadDTO;
import user.dao.UserUploadDAO;

@Service
public class UserUploadSeviceImpl implements UserUploadService {
	@Autowired
	private UserUploadDAO userUploadDAO;
	
	@Override
	public void upload(List<UserUploadDTO> userImageList) {
		userUploadDAO.saveAll(userImageList);
		
	}
	
	@Override
	public List<UserUploadDTO> uploadList() {
		//return userUploadDAO.findAll();
		return userUploadDAO.findAllByOrderBySeqDesc(); //쿼리 메소드
	}
}
