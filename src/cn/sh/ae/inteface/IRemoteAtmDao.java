package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atm;

public interface IRemoteAtmDao extends Serializable {
	public List<Atm> getAtmpStatus(Connection conn);

}
