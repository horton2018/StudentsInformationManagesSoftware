package Software;

import javax.swing.UIManager;

import com.mqd.contact.dao.DAOimpl;
import com.mqd.contact.swing.Login;

public class Start {
	
	public static void main(String[]  args) {
		
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// ����BeautyEye����
			UIManager.put("RootPane.setupButtonVisible", false);//����BeautyEye�����е����ð�ť
		} catch (Exception e) {   
			e.printStackTrace();
		}

		Login frame = new Login();
		frame.setVisible(true);
	}

}
