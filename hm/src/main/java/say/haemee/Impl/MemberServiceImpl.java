package say.haemee.Impl;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import say.haemee.Mapper.IMemberMapper;
import say.haemee.Service.IMemberService;
import say.haemee.VO.MemberVO;

@Repository
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	IMemberMapper memMapper;
	
	@Override
	public List<MemberVO> getMemList() {
	
		return memMapper.getMemList();
	}//getMemList
}//class
