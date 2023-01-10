package com.ezen.doran.service.userPage;

import java.util.List;

import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.UserDTO;

public interface UserPageService {

	void updateUser(UserDTO userDTO);

	List<PlayDTO> selectMyPlayList();


}
