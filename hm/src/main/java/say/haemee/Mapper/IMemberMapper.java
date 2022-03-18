package say.haemee.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import say.haemee.VO.MemberVO;

@Mapper
public interface IMemberMapper {
	public List<MemberVO> getMemList();
}//interface
