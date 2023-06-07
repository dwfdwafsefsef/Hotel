package edu.spring.hotel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.hotel.domain.ReservationVO;
import edu.spring.hotel.persistence.ReservationDAO;
import edu.spring.hotel.persistence.RoomDAO;

@Service
public class ReservationServiceImple implements ReservationService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReservationServiceImple.class);
	
	@Autowired
	private ReservationDAO reservationDAO;

	@Autowired
	private RoomDAO roomDAO;
	
	@Transactional(value="transactionManager")
	@Override
	public int create(ReservationVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		reservationDAO.insert(vo);		
		roomDAO.updateRoomCnt(-1, vo.getRoomId());
		logger.info("�ܿ� ���� �� �����Ϸ�");
		return 1;
	}

	@Override
	public List<ReservationVO> read(String memberId) {
		logger.info("read() ȣ��");
		return reservationDAO.select(memberId);
	}

	@Transactional(value="transactionManager")
	@Override
	public int delete(int reservationId, int roomId) {
		logger.info("delete() ȣ�� : reservationId = " + reservationId);
		reservationDAO.delete(reservationId, roomId);
		roomDAO.updateRoomCnt(1, roomId);
		logger.info("�ܿ� ���� �� ���� �Ϸ�");
		return 1;
	}
	
}
