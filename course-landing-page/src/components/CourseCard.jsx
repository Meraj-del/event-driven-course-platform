import {
  enrollCourse,
  buyCourse,
} from "../services/courseApi";

function CourseCard({
  course,
  setMessage,
}) {
  const enroll = async () => {
    const msg =
      await enrollCourse(course.id);

    setMessage(msg);
  };

  const buy = async () => {
    const msg =
      await buyCourse(course.id);

    setMessage(msg);
  };

  return (
    <div className="course-card">
      <img
        src={course.image}
        alt={course.title}
      />

      <div className="course-content">
        <h3>{course.title}</h3>

        <p>{course.instructor}</p>

        <p>
          ⭐ {course.rating}
        </p>

        <p>
          {course.students}
          {" "}Students
        </p>

        <h4>{course.price}</h4>

        <div className="actions">
          <button
            className="enroll"
            onClick={enroll}
          >
            Enroll
          </button>

          <button
            className="buy"
            onClick={buy}
          >
            Buy
          </button>
        </div>
      </div>
    </div>
  );
}

export default CourseCard;