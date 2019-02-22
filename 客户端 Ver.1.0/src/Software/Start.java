package Software;

import javax.swing.UIManager;

import com.mqd.contact.dao.DAOimpl;
import com.mqd.contact.swing.Login;

public class Start {
	
	public static void main(String[]  args) {
		
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// 启用BeautyEye主题
			UIManager.put("RootPane.setupButtonVisible", false);//隐藏BeautyEye主题中的设置按钮
		} catch (Exception e) {   
			e.printStackTrace();
		}

		Login frame = new Login();
		frame.setVisible(true);
	}

}
