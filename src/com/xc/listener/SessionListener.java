package com.xc.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	private static HashMap hUserName = new HashMap();// 保存sessionID和username的映射
														// /**以下是实现HttpSessionListener中的方法**/

	public void sessionCreated(HttpSessionEvent se) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		hUserName.remove(se.getSession().getId());
	}

	public static void removeSession(HttpSession session) {
		hUserName.remove(session.getId());
	}

	/*
	 * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	 * 
	 * @param sUserName String-登录的用户名称
	 * 
	 * @return boolean-该用户是否已经登录过的标志
	 */

	public static boolean isAlreadyEnter(HttpSession session, String sUserName) {
		boolean flag = false;
		if (hUserName.containsValue(sUserName)) {
			// 如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
			flag = true;
			// // 遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
			// Iterator iter = hUserName.entrySet().iterator();
			// while (iter.hasNext()) {
			// Map.Entry entry = (Map.Entry) iter.next();
			// Object key = entry.getKey();
			// Object val = entry.getValue();
			// if (((String) val).equals(sUserName)) {
			// hUserName.remove(key);
			// }
			// }
			// hUserName.put(session.getId(), sUserName);//
			// 添加现在的sessionID和username
			System.out.println(session.getId() + hUserName);
		} else {
			// 如果该用户没登录过，直接添加现在的sessionID和username
			flag = false;
			hUserName.put(session.getId(), sUserName);
			System.out.println("hUserName = " + hUserName);
		}
		return flag;
	}

	/*
	 * isOnline-用于判断用户是否在线
	 * 
	 * @param session HttpSession-登录的用户名称
	 * 
	 * @return boolean-该用户是否在线的标志
	 */
	public static boolean isOnline(HttpSession session) {
		boolean flag = true;
		if (hUserName.containsKey(session.getId())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
