import { useStats } from "../hooks/useStats";

function LiveStats() {
  const stats = useStats();

  return (
    <section className="stats">
      <div>
        <h2>{stats.enrollments}</h2>
        <p>Enrollments</p>
      </div>

      <div>
        <h2>{stats.purchases}</h2>
        <p>Purchases</p>
      </div>
    </section>
  );
}

export default LiveStats;