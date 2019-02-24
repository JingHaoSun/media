package com.innovate.media.utils;

import com.innovate.media.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该表返回形式的工具类
 * @author sjh
 *
 */
public class ChangeResultTypeUtils {

	/**
	 * 菜品分类
	 * @param list
	 * @return
	 */
	public static List<Map> changeAdminResultType(List<Admin> list) {
		Admin[] admin = list.toArray(new Admin[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Admin admin1 : admin) {
			Map map = null;
				map = FormatBeanUtils.formatBean(admin1);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeChinaResultType(List<China> list) {
		China[] china = list.toArray(new China[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(China china1 : china) {
			Map map = null;
			map = FormatBeanUtils.formatBean(china1);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeClientResultType(List<?> list) {
		Client[] client = list.toArray(new Client[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Client clientl : client) {
			Map map = null;
			map = FormatBeanUtils.formatBean(clientl);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeCompanyResultType(List<?> list) {
		Company[] companies = list.toArray(new Company[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Company company : companies) {
			Map map = null;
			map = FormatBeanUtils.formatBean(company);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeClientOccupationResultType(List<?> list) {
		ClientOccupation[] clientOccupations = list.toArray(new ClientOccupation[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(ClientOccupation clientOccupation : clientOccupations) {
			Map map = null;
			map = FormatBeanUtils.formatBean(clientOccupation);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeWorkCategoryResultType(List<?> list) {
		WorkCategory[] workCategories = list.toArray(new WorkCategory[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(WorkCategory workCategory : workCategories) {
			Map map = null;
			map = FormatBeanUtils.formatBean(workCategory);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeWorkResultType(List<?> list) {
		Work[] works = list.toArray(new Work[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Work work : works) {
			Map map = null;
			map = FormatBeanUtils.formatBean(work);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changePostionResultType(List<?> list) {
		Postion[] postions = list.toArray(new Postion[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Postion postion : postions) {
			Map map = null;
			map = FormatBeanUtils.formatBean(postion);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeCommentResultType(List<?> list) {
		Comment[] comments = list.toArray(new Comment[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(Comment comment : comments) {
			Map map = null;
			map = FormatBeanUtils.formatBean(comment);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeCommentReplyResultType(List<?> list) {
		CommentReply[] commentReplies = list.toArray(new CommentReply[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(CommentReply commentReply : commentReplies) {
			Map map = null;
			map = FormatBeanUtils.formatBean(commentReply);
			listMap.add(map);
		}
		return listMap;
	}

	public static List<Map> changeAdminLogResultType(List<?> list) {
		AdminLog[] adminLogs = list.toArray(new AdminLog[list.size()]);
		List<Map> listMap = new ArrayList<>();
		for(AdminLog adminLog : adminLogs) {
			Map map = null;
			map = FormatBeanUtils.formatBean(adminLog);
			listMap.add(map);
		}
		return listMap;
	}
//	/**
//	 * 员工
//	 * @param list
//	 * @return
//	 */
//	public static List<Map> changeEmployeeResultType(List<Employee> list) throws IllegalAccessException {
//		Employee[] employee = list.toArray(new Employee[list.size()]);
//		List<Map> listMap = new ArrayList<>();
//		for(Employee objectEmployee : employee) {
//			Map map = null;
//			map = FormatBeanUtils.formatBean(objectEmployee);
//		listMap.add(map);
//	}
//	return listMap;
//	}
//	/**
//	 * 订单
//	 * @param list
//	 * @return
//	 */
//	public static List<Map> changeSellOrderResultType(List<SellOrder> list) {
//		SellOrder[] sellOrder = list.toArray(new SellOrder[list.size()]);
//		List<Map> listMap = new ArrayList<>();
//		for(SellOrder objectSellOrder : sellOrder) {
//			Map<String, Comparable> map = new HashMap<String, Comparable>(30);
//			map.put("id", objectSellOrder.getId());
//			map.put("order_number", objectSellOrder.getOrderNumber());
//			map.put("user_id", objectSellOrder.getUserId());
//			map.put("user_name", objectSellOrder.getUserName());
//			map.put("bind_phone", objectSellOrder.getBindPhone());
//			map.put("order_comment", objectSellOrder.getOrderComment());
//			map.put("fee", objectSellOrder.getFee());
//			map.put("create_time", objectSellOrder.getCreateTime());
//			map.put("finish_time", objectSellOrder.getFinishTime());
//			map.put("tableware_count", objectSellOrder.getTablewareCount());
//			map.put("price", objectSellOrder.getPrice());
//			map.put("pay_way", objectSellOrder.getPayWay());
//			map.put("evalute_status", objectSellOrder.getEvaluteStatus());
//			map.put("pay_status", objectSellOrder.getPayStatus());
//			map.put("pay_time", objectSellOrder.getPayTime());
//			map.put("receiver_name", objectSellOrder.getReceiverName());
//			map.put("sex", objectSellOrder.getSex());
//			map.put("city_id", objectSellOrder.getCityId());
//			map.put("city_name", objectSellOrder.getCityName());
//			map.put("phone", objectSellOrder.getPhone());
//			map.put("address", objectSellOrder.getAddress());
//			map.put("distance", objectSellOrder.getDistance());
//			map.put("start_price", objectSellOrder.getStartPrice());
//			map.put("package_price", objectSellOrder.getPackagePrice());
//			listMap.add(map);
//		}
//		return listMap;
//	}
//	/**
//	 * 详细订单
//	 * @param list
//	 * @return
//	 */
//	public static List<Map> changeOrderDetailResultType(List<OrderDetail> list) {
//		OrderDetail[] orderDetail = list.toArray(new OrderDetail[list.size()]);
//		List<Map> listMap = new ArrayList<>();
//		for(OrderDetail objectOrderDetail : orderDetail) {
//			Map map = null;
//			map = FormatBeanUtils.formatBean(objectOrderDetail);
//		listMap.add(map);
//	}
//	return listMap;
//	}
//	/**
//	 *
//	 * @param list
//	 * @return
//	 */
//	public static List<Map> changeOrderCommentResultType(List<OrderComment> list) {
//		OrderComment[] orderComment = list.toArray(new OrderComment[list.size()]);
//		List<Map> listMap = new ArrayList<>();
//		for(OrderComment objectOrderComment : orderComment) {
//			Map map = null;
//			map = FormatBeanUtils.formatBean(objectOrderComment);
//		listMap.add(map);
//	}
//	return listMap;
//	}
}