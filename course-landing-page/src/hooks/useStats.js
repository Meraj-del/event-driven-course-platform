import { useEffect, useState } from "react";

const API_BASE = "http://192.168.1.50:8090";

export function useStats() {
  const [stats, setStats] = useState({
    enrollments: 0,
    purchases: 0,
  });

  const fetchStats = async () => {
    try {
      const res = await fetch(`${API_BASE}/api/stats`);
      const data = await res.json();
      setStats(data);
    } catch (err) {
      console.log("Stats fetch failed", err);
    }
  };

  useEffect(() => {
    fetchStats(); 

    const interval = setInterval(() => {
      fetchStats();
    }, 2000); 

    return () => clearInterval(interval);
  }, []);

  return stats;
}