import CourseCard from "./CourseCard";
import { courses } from "../data/courses";

function CourseGrid({
  setMessage,
}) {
  return (
    <section className="courses">
      <h2>
        Featured Courses
      </h2>

      <div className="course-grid">
        {courses.map((course) => (
          <CourseCard
            key={course.id}
            course={course}
            setMessage={setMessage}
          />
        ))}
      </div>
    </section>
  );
}

export default CourseGrid;