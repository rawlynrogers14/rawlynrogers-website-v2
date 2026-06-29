import { useEffect, useState } from "react";
import "./ImageCarousel.css";

const BACKEND_URL = "http://localhost:8080";

export default function ImageCarousel({ images = [] }) {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [isPaused, setIsPaused] = useState(false);

  useEffect(() => {
    if (isPaused || images.length <= 1) return;

    const timer = setInterval(() => {
      setCurrentIndex((index) =>
        index === images.length - 1 ? 0 : index + 1
      );
    }, 3000);

    return () => clearInterval(timer);
  }, [isPaused, images.length]);

  if (!images.length) {
    return null;
  }

  const currentImage = images[currentIndex];

  return (
    <div
      className="image-carousel"
      onMouseEnter={() => setIsPaused(true)}
      onMouseLeave={() => setIsPaused(false)}
    >
      <img
        src={`${BACKEND_URL}${currentImage.filePath}`}
        alt={
          currentImage.description ||
          currentImage.fileName ||
          "Carousel image"
        }
        className="image-carousel-img"
      />

      {images.length > 1 && (
        <div className="image-carousel-dots">
          {images.map((image, index) => (
            <button
              key={image.id}
              type="button"
              className={
                index === currentIndex
                  ? "image-carousel-dot active"
                  : "image-carousel-dot"
              }
              onClick={() => setCurrentIndex(index)}
              aria-label={`Show slide ${index + 1}`}
            />
          ))}
        </div>
      )}
    </div>
  );
}