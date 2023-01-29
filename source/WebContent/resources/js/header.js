function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));

};

function login() {
	location.href = getContextPath()+"/login";
}
function logout() {
	location.href = getContextPath()+"/logout.do";
}
function mypage() {
	location.href = getContextPath()+"/mypage";
}


function main() {
	location.href = getContextPath()+"/main";
}


