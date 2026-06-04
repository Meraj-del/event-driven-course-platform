const API_BASE = "http://192.168.1.50:8090";

export const enrollCourse = async (courseId) => {
  const response = await fetch(
    `${API_BASE}/api/course/enroll?courseId=${courseId}`,
    { method: "POST" }
  );

  const data = await response.json();
  return data.message;
};

export const buyCourse = async (courseId) => {
  const response = await fetch(
    `${API_BASE}/api/course/buy?courseId=${courseId}`,
    { method: "POST" }
  );

  const data = await response.json();
  return data.message;
};