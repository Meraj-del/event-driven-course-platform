function Navbar() {
  return (
    <nav className="navbar">
      <h2>LearnHub</h2>

      <div className="nav-links">
        <a href="#">Courses</a>
        <a href="#">Categories</a>
        <a href="#">My Learning</a>
        <a href="#">About</a>
      </div>

      <button className="login-btn">
        Login
      </button>
    </nav>
  );
}

export default Navbar;