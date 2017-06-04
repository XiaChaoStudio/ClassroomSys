package com.xc.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.PageBean;
import com.xc.domain.Timeinfo;
import com.xc.service.ClassroomService;

public class ClassroomAction extends ActionSupport implements ModelDriven<Classroom> {
	private Classroom classroom = new Classroom();

	private ClassroomService classroomService;
	private Integer currPage = 1;
	private Date date;
	private String week;
	private int timeid;
	private int condition;
	private String content;
	private int islonguse;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public int getIslonguse() {
		return islonguse;
	}

	public void setIslonguse(int islonguse) {
		this.islonguse = islonguse;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTimeid() {
		return timeid;
	}

	public void setTimeid(int timeid) {
		this.timeid = timeid;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public void setClassroomService(ClassroomService classroomService) {
		this.classroomService = classroomService;
	}

	public String listAll() {
		PageBean<Classroom> pageBean = classroomService.findByPage(currPage, classroom.getKind());
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list_success";
	}

	public String alter() {
		classroomService.alter(classroom);
		if (classroom.getKind() == 1) {
			return "alter_success1";
		} else {
			return "alter_success2";
		}

	}

	public String add() {
		Classroom result = classroomService.getByName(classroom.getCname());
		if (result == null) {
			classroomService.add(classroom);
			if (classroom.getKind() == 1) {
				return "add_success1";
			} else {
				return "add_success2";
			}
		} else {
			this.addActionError("名称重复");
			if (result.getKind() == 1) {
				return "add_fail1";
			} else {
				return "add_fail2";
			}

		}

	}

	public String beforeAlter() {
		classroom = classroomService.getByName(classroom.getCname());
		return "before_success";
	}

	public String query() {
		System.out.println(week + timeid);
		ActionContext.getContext().getValueStack().push(classroom);
		List<Timeinfo> timeInfos = classroomService.query();

		ActionContext.getContext().getValueStack().set("timeInfos", timeInfos);
		if (islonguse == 0) {
			return "short_query_success";
		} else {
			return "long_query_success";
		}

	}

	public String delete() {
		Classroom result = classroomService.getByName(classroom.getCname());
		if (result != null) {
			classroomService.delete(result);
			if (result.getKind() == 1) {
				return "delete_success1";
			} else {
				return "delete_success2";
			}
		}
		return "delete_fail";
	}

	public String find() {
		Date date_now = new Date(System.currentTimeMillis());
		if (date != null && date_now.compareTo(date) > 0) {
			return "find_invalid";
		}
		List<Classroom> classrooms = null;
		List<Classroom_use> classroom_uses = null;
		ActionContext.getContext().getSession().put("date", date);
		ActionContext.getContext().getSession().put("week", week);
		ActionContext.getContext().getSession().put("timeid", timeid);

		if (week.equals("无")) {
			if (date == null) {
				return "find_null";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
			String week_date = sdf.format(date);
			classroom_uses = classroomService.findUse(date, timeid);
			List<Classroom_use> classroom_uses_week = classroomService.findUseByWeek(week_date, timeid);
			classroom_uses.addAll(classroom_uses_week);
		} else {
			classroom_uses = classroomService.findUseByWeek(week, timeid);
		}
		for (Classroom_use classroom_use : classroom_uses) {
			System.out.println(classroom_use);
		}

		if (condition == 1) {
			classrooms = classroomService.findByName(content, classroom.getKind());
			if (classrooms.size() == 1) {
				classroom = classrooms.get(0);
				for (Classroom_use classroom_use : classroom_uses) {
					if (classroom_use.getCname().equals(classroom.getCname())) {
						classrooms = null;
						break;
					}
				}
			}
		} else if (condition == 2) {
			classrooms = classroomService.findBySize(Integer.parseInt(content), classroom.getKind());
			for (int i = 0; i < classrooms.size(); i++) {
				for (Classroom_use classroom_use : classroom_uses) {
					if (classroom_use.getCname().equals(classrooms.get(i).getCname())) {
						classrooms.remove(i);
						i--;
						break;
					}
				}
			}
		} else {
			classrooms = classroomService.findBySite(content, classroom.getKind());
			for (int i = 0; i < classrooms.size(); i++) {
				for (Classroom_use classroom_use : classroom_uses) {
					if (classroom_use.getCname().equals(classrooms.get(i).getCname())) {
						classrooms.remove(i);
						i--;
						break;
					}
				}
			}
		}

		if (classrooms == null || classrooms.size() <= 0) {
			return "find_null";
		} else {
			ActionContext.getContext().getValueStack().set("list", classrooms);
			return "find_success";
		}

	}

	@Override
	public Classroom getModel() {
		return classroom;
	}

}
