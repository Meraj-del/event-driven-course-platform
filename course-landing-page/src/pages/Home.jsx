import { useState } from "react";

import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import LiveStats from "../components/LiveStats";
import CourseGrid from "../components/CourseGrid";
import Testimonials from "../components/Testimonials";
import Footer from "../components/Footer";

function Home() {

  const [message, setMessage] = useState("");

  return (
    <>
      <Navbar />

      <Hero />

      <LiveStats />

      <CourseGrid setMessage={setMessage} />

      {message && (
        <div className="message">
          {message}
        </div>
      )}

      <Testimonials />

      <Footer />
    </>
  );
}

export default Home;