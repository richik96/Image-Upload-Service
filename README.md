# Image Upload Service

A simple Spring Boot application that allows users to upload, view, and delete JPG/JPEG images. Uploaded images are displayed in a card-style thumbnail gallery, where users can view the full image or delete it with a single click.

## Features

- Upload images (`.jpg`, `.jpeg` formats only)
- View uploaded images as card thumbnails
- Click a thumbnail to view the full image
- Delete images directly from the gallery

## Demo

_Screenshot or GIF here if available_

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6+
- (Optional) Docker

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/richik96/Image-Upload-Service.git
    cd Image-Upload-Service
    ```

2. Build the project:
    ```bash
    mvn clean package
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```
    The app will be available at `http://localhost:8080/`.

### Docker (Optional)

To run with Docker:

```bash
docker build -t image-upload-service .
docker run -p 8080:8080 image-upload-service
```

## Usage

- Open [http://localhost:8080/](http://localhost:8080/) in your browser.
- Use the upload form to add new images (JPG/JPEG only).
- Browse thumbnails in the gallery.
- Click "View" on a card to see the full image.
- Click "Delete" to remove an image.

## API (if exposed)

| Method | Endpoint        | Description              |
| ------ | -------------- | ------------------------ |
| POST   | `/upload`      | Upload a new image       |
| GET    | `/images`      | List all images          |
| GET    | `/images/{id}` | View/download an image   |
| DELETE | `/images/{id}` | Delete an image          |

_If your API differs, update accordingly._

## Configuration

- Default upload folder: `uploads/` in project root (configurable in `application.properties`)
- Only `.jpg` and `.jpeg` files are allowed.

## Project Structure

```
src/
 └── main/
      ├── java/
      │    └── ... (controllers, services, models)
      └── resources/
           ├── static/
           ├── templates/
           └── application.properties
uploads/
```

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](LICENSE)

---

_Maintained by [richik96](https://github.com/richik96)._
