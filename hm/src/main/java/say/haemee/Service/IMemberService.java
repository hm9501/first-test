package say.haemee.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import say.haemee.VO.MemberVO;

@Service
public interface IMemberService {
	public List<MemberVO> getMemList();
}//interface
