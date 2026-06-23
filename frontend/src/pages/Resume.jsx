function Resume() {
  return (
    <section className="page">
      <h1>Resume</h1>

      <div className="resume-section">
        <h2>Short-Formatted Resume</h2>
        <p>Download my concise professional resume.</p>
        <a href="/resume-short.pdf" download>
          Download Short Resume
        </a>
      </div>

      <div className="resume-section">
        <h2>Long-Format CV</h2>
        <p>Download my full CV with expanded experience and projects.</p>
        <a href="/cv-long.pdf" download>
          Download Long CV
        </a>
      </div>
    </section>
  );
}

export default Resume;