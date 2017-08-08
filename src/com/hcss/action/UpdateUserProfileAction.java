/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.hcss.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hcss.bean.UserProfileFormBean;
import com.hcss.delegate.SecurityUserDelegate;
import com.hcss.utill.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * MyEclipse Struts Creation date: 09-07-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="failure" path="/Status.jsp"
 * @struts.action-forward name="success" path="/Status.jsp"
 */
public class UpdateUserProfileAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		UserProfileFormBean userProfileFormBean = new UserProfileFormBean();
		Map map = request.getParameterMap();
		System.out.println("haiiiiiiiiii");
		boolean flag = false;
		try {
			BeanUtils.populate(userProfileFormBean, map);
			userProfileFormBean.setUserid((Integer) session
					.getAttribute("userid"));
			flag = new SecurityUserDelegate()
					.updateUserProfile(userProfileFormBean);
			if (flag) {
				request.setAttribute("status",
						UtilConstants._USER_PROFILE_UPDATE);
				return mapping.findForward("success");
			} else {
				request.setAttribute("status",
						UtilConstants._USER_PROFILE_UPDATE_FAIL);
				return mapping.findForward("failure");
			}
		} catch (Exception ce) {
			request.setAttribute("status", ce.getMessage());
			return mapping.findForward("failure");
		}
	}
}