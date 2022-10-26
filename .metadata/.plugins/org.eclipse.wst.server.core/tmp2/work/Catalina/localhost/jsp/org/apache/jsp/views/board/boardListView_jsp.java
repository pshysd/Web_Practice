/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.82
 * Generated at: 2022-10-25 15:31:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.common.model.vo.PageInfo;
import java.util.ArrayList;
import com.kh.board.model.vo.Board;
import com.kh.member.model.vo.Member;

public final class boardListView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/board/../common/menubar.jsp", Long.valueOf(1666542945549L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.kh.common.model.vo.PageInfo");
    _jspx_imports_classes.add("com.kh.board.model.vo.Board");
    _jspx_imports_classes.add("java.util.ArrayList");
    _jspx_imports_classes.add("com.kh.member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	PageInfo pi = (PageInfo) request.getAttribute("pi"); // 페이징 바 만들기
ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list"); // 조회된 내용을 출력

//	자주 쓰는 필드 값 미리 빼두기
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write(".outer {\r\n");
      out.write("	background-color: black;\r\n");
      out.write("	color: white;\r\n");
      out.write("	width: 1000px;\r\n");
      out.write("	height: 550px;\r\n");
      out.write("	margin: auto;\r\n");
      out.write("	margin-top: 50px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".list-area {\r\n");
      out.write("	border: 1px solid white;\r\n");
      out.write("	text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".list-area>tbody>tr  :hover {\r\n");
      out.write("	background-color: gray;\r\n");
      out.write("	cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("\r\n");

	// 로그인한 사용자의 정보를 sesion에 담았기 때문에 // 이 웹 애플리케이션의 어디서든지 해당 키 값을 제시해서 로그인한 사용자의 정보를 가져올 수 있음 
Member loginUser = (Member) session.getAttribute("loginUser"); // System.out.println(loginUser); 
// 로그인 전 menubar.jsp 로딩 시 : null 
// 로그인 후 menubar.jsp 로딩 시 : 로그인한 회원의 정보가 담긴 Member 객체 
String contextPath = request.getContextPath(); // 성공 시 알람 문구 또한 session에 담았기 때문에 // session으로부터 뽑기 
String alertMsg = (String) session.getAttribute("alertMsg");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\" />\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Latest compiled and minified CSS -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css\" />\r\n");
      out.write("\r\n");
      out.write("<!-- Popper JS -->\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Latest compiled JavaScript -->\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("#login-form, #user-info {\r\n");
      out.write("	float: right;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#user-info a {\r\n");
      out.write("	text-decoration: none;\r\n");
      out.write("	color: black;\r\n");
      out.write("	font-size: 12px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".nav-area {\r\n");
      out.write("	background-color: black;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".menu {\r\n");
      out.write("	display: table-cell; /* 인라인 요소처럼 배치 가능 */\r\n");
      out.write("	height: 50px;\r\n");
      out.write("	width: 150px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".menu a {\r\n");
      out.write("	text-decoration: none;\r\n");
      out.write("	color: white;\r\n");
      out.write("	font-size: 20px;\r\n");
      out.write("	font-weight: bold;\r\n");
      out.write("	display: block;\r\n");
      out.write("	width: 100%;\r\n");
      out.write("	height: 100%;\r\n");
      out.write("	line-height: 50px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".menu a:hover {\r\n");
      out.write("	background-color: darkgrey;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	<script>\r\n");
      out.write("		// script 태그 내에도 스크립틀릿과 같은 jsp 요소를 쓸 수 있다.\r\n");
      out.write("\r\n");
      out.write("		let msg = '");
      out.print(alertMsg);
      out.write("';\r\n");
      out.write("		// 성공적으로 로그인이 되었습니다 / \"null\"\r\n");
      out.write("\r\n");
      out.write("		if (msg !== 'null') {\r\n");
      out.write("			alert(msg);\r\n");
      out.write("			// 알림창을 띄워준 후에 session에 담긴 값을 지워줘야 한다\r\n");
      out.write("	");
session.removeAttribute("alertMsg");
      out.write("\r\n");
      out.write("		}\r\n");
      out.write("		console.log(msg);\r\n");
      out.write("	</script>\r\n");
      out.write("\r\n");
      out.write("	<h1 align=\"center\">Welcome D Class</h1>\r\n");
      out.write("\r\n");
      out.write("	<div class=\"login-area\">\r\n");
      out.write("\r\n");
      out.write("		");

			if (loginUser == null) {
		
      out.write("\r\n");
      out.write("		<!-- 로그인 전에 보여지는 로그인 form -->\r\n");
      out.write("		<!-- 로그인 버튼을 클릭했을 때 이동하고자 하는 위치 : http://localhost:8888/jsp/login.me \r\n");
      out.write("		            \r\n");
      out.write("		            	절대경로 : /JSP/login.me\r\n");
      out.write("		            	상대경로 : login.me\r\n");
      out.write("		            -->\r\n");
      out.write("		<form id=\"login-form\" action=\"");
      out.print(contextPath);
      out.write("/login.me\" method=\"post\">\r\n");
      out.write("			<table>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>아이디 :</th>\r\n");
      out.write("					<td><input type=\"text\" name=\"userId\" id=\"\" required /></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>비밀번호 :</th>\r\n");
      out.write("					<td><input type=\"password\" name=\"userPwd\" id=\"\" required /></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th colspan=\"2\">\r\n");
      out.write("						<button type=\"submit\">로그인</button>\r\n");
      out.write("						<button type=\"button\" onclick=\"enrollPage();\">회원가입</button>\r\n");
      out.write("					</th>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</table>\r\n");
      out.write("		</form>\r\n");
      out.write("\r\n");
      out.write("		<script>\r\n");
      out.write("							function enrollPage() {\r\n");
      out.write("\r\n");
      out.write("								// location.href =\"");
      out.print(contextPath);
      out.write("/views/member/memberEnrollForm.jsp\";\r\n");
      out.write("								// 웹 애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약\r\n");
      out.write("\r\n");
      out.write("								// 단순한 페이지 요청이라고 하더라도 반드시 Servlet을 거쳐갈 것\r\n");
      out.write("								location.href = \"");
      out.print(contextPath);
      out.write("\r\n");
      out.write("			/enrollForm.me\";\r\n");
      out.write("			}\r\n");
      out.write("		</script>\r\n");
      out.write("		");

			} else {
		
      out.write("\r\n");
      out.write("		<!-- 로그인 성공 후에 보여지는 프로필 화면 -->\r\n");
      out.write("\r\n");
      out.write("		<div id=\"user-info\">\r\n");
      out.write("			<b> ");
      out.print(loginUser.getUserName());
      out.write("님\r\n");
      out.write("			</b> 환영합니다~ <br /> <br />\r\n");
      out.write("\r\n");
      out.write("			<div align=\"center\">\r\n");
      out.write("\r\n");
      out.write("				<a href=\"");
      out.print(contextPath);
      out.write("/myPage.me\">마이페이지</a> <a\r\n");
      out.write("					href=\"");
      out.print(contextPath);
      out.write("/logout.me\">로그아웃</a>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("		");

			}
		
      out.write("\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	<br clear=\"both\" />\r\n");
      out.write("	<!-- float 속성 해제-->\r\n");
      out.write("	<br />\r\n");
      out.write("	<div class=\"nav-area\" align=\"center\">\r\n");
      out.write("		<div class=\"menu\">\r\n");
      out.write("			<a href=\"");
      out.print(contextPath);
      out.write("\">HOME</a>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"menu\">\r\n");
      out.write("			<a href=\"");
      out.print(contextPath);
      out.write("/list.no\">공지사항</a>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"menu\">\r\n");
      out.write("			<a href=\"");
      out.print(contextPath);
      out.write("/list.bo?currentPage=1\">일반게시판</a>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"menu\">\r\n");
      out.write("			<a href=\"\">사진게시판</a>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div class=\"outer\">\r\n");
      out.write("		<br>\r\n");
      out.write("		<h2 align=\"center\">일반 게시판</h2>\r\n");
      out.write("		<br>\r\n");
      out.write("\r\n");
      out.write("		<!-- 로그인한 회원만 보여지는 글 작성버튼 -->\r\n");
      out.write("		");
 if(loginUser != null) {
      out.write("\r\n");
      out.write("		<div style=\"width: 850px;\" align=\"right\">\r\n");
      out.write("			<a href=\"");
      out.print(contextPath);
      out.write("/enrollForm.bo\" class=\"btn btn-secondary\">글 작성</a>\r\n");
      out.write("		</div>\r\n");
      out.write("		");
} 
      out.write("\r\n");
      out.write("		<br>\r\n");
      out.write("		<!-- 조회한 게시물들이 보여질 자리 -->\r\n");
      out.write("		<table align=\"center\" class=\"list-area\">\r\n");
      out.write("			<thead>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th width=\"70\">글번호</th>\r\n");
      out.write("					<th width=\"70\">카테고리</th>\r\n");
      out.write("					<th width=\"300\">제목</th>\r\n");
      out.write("					<th width=\"100\">작성자</th>\r\n");
      out.write("					<th width=\"50\">조회수</th>\r\n");
      out.write("					<th width=\"100\">작성일</th>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</thead>\r\n");
      out.write("			<tbody>\r\n");
      out.write("				");

					if (list.isEmpty()) {
				
      out.write("\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td colspan=\"6\">조회된 리스트가 없습니다.</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				");

					} else {
				
      out.write("\r\n");
      out.write("				");

					for (Board b : list) {
				
      out.write("\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td>");
      out.print(b.getBoardNo());
      out.write("</td>\r\n");
      out.write("					<td>");
      out.print(b.getCategory());
      out.write("</td>\r\n");
      out.write("					<td>");
      out.print(b.getBoardTitle());
      out.write("</td>\r\n");
      out.write("					<td>");
      out.print(b.getBoardWriter());
      out.write("</td>\r\n");
      out.write("					<td>");
      out.print(b.getCount());
      out.write("</td>\r\n");
      out.write("					<td>");
      out.print(b.getCreateDate());
      out.write("</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				");

					}
				
      out.write("\r\n");
      out.write("				");

					}
				
      out.write("\r\n");
      out.write("				<!--\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td>10</td>\r\n");
      out.write("					<td>게임</td>\r\n");
      out.write("					<td>게시글 제목입니다.</td>\r\n");
      out.write("					<td>user02</td>\r\n");
      out.write("					<td>78</td>\r\n");
      out.write("					<td>2022-05-01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				  -->\r\n");
      out.write("			</tbody>\r\n");
      out.write("		</table>\r\n");
      out.write("		<br> <br>\r\n");
      out.write("\r\n");
      out.write("		<!-- 페이징 바 만들기 -->\r\n");
      out.write("		<div align=\"center\" class=\"paging-area\">\r\n");
      out.write("			<!-- \r\n");
      out.write("			<button>1</button>\r\n");
      out.write("			<button>2</button>\r\n");
      out.write("			<button>3</button>\r\n");
      out.write("			<button>4</button>\r\n");
      out.write("			<button>5</button>\r\n");
      out.write("			<button>6</button>\r\n");
      out.write("			<button>7</button>\r\n");
      out.write("			<button>8</button>\r\n");
      out.write("			<button>9</button>\r\n");
      out.write("			<button>10</button>\r\n");
      out.write("			 -->\r\n");
      out.write("			");

				if (currentPage != 1) {
			
      out.write("\r\n");
      out.write("			<button\r\n");
      out.write("				onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(currentPage - 1);
      out.write("'\">&lt;</button>\r\n");
      out.write("			");

				}
			
      out.write("\r\n");
      out.write("			");

				for (int p = startPage; p <= endPage; p++) {
			
      out.write("\r\n");
      out.write("			");

				if (p != currentPage) {
			
      out.write("\r\n");
      out.write("			<button\r\n");
      out.write("				onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(p);
      out.write('\'');
      out.write('"');
      out.write('>');
      out.print(p);
      out.write("</button>\r\n");
      out.write("			");

				} else {
			
      out.write("\r\n");
      out.write("			<button disabled>");
      out.print(p);
      out.write("</button>\r\n");
      out.write("			");

				}
			
      out.write("\r\n");
      out.write("			");

				}
			
      out.write("\r\n");
      out.write("			");

				if (currentPage != maxPage) {
			
      out.write("\r\n");
      out.write("			<button\r\n");
      out.write("				onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(currentPage + 1);
      out.write("'\">&gt;</button>\r\n");
      out.write("			");

				}
			
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
