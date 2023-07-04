/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beta;

/**
 *
 * @author Davs
 */
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.IOException;

public class BoletaPDF {

    public static void main(String[] args) {
        try {
            // Crear un nuevo archivo PDF
            File file = new File("boleta.pdf");
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A6);

            // Crear fuente personalizada
            PdfFont font = PdfFontFactory.createFont("arial.ttf", true);

            // Encabezado
            Paragraph header = new Paragraph("Boleta de reserva")
                    .setFont(font)
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(header);
            
                        // Ruta de la imagen
            String imagePath = "logo.png";

            // Cargar la imagen
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);

            float logoWidth = 100;  // Ancho del logo en puntos
            float logoHeight = 38; // Altura del logo en puntos
            image.setWidth(logoWidth);
            image.setHeight(logoHeight);

            // Obtener el ancho del documento
            float docWidth = pdf.getDefaultPageSize().getWidth();

            // Colocar la imagen en la parte superior derecha
            float xPosition = docWidth - logoWidth - 20; // 20 puntos de margen desde el borde derecho
            float yPosition = pdf.getDefaultPageSize().getTop() - logoHeight - 10; // 20 puntos de margen desde el borde superior
            image.setFixedPosition(xPosition, yPosition);

            // Agregar la imagen al documento
            document.add(image);

            // Agregar la imagen al documento
            document.add(image);


            // Información del cliente
            Paragraph customerInfo = new Paragraph()
                    .add(new Text("Cliente: Juan Pérez\n"))
                    .add(new Text("Dirección: Calle 123, Ciudad\n"))
                    .add(new Text("Fecha: 4 de Julio, 2023\n"))
                    .setFont(font)
                    .setFontSize(12);
            document.add(customerInfo);

            // Detalles de los productos
            Paragraph productsHeader = new Paragraph("Detalles de la reserva")
                    .setFont(font)
                    .setBold()
                    .setFontSize(12);
            document.add(productsHeader);

            // Productos
            Paragraph product1 = new Paragraph("Producto 1: $10")
                    .setFont(font)
                    .setFontSize(12);
            Paragraph product2 = new Paragraph("Producto 2: $20")
                    .setFont(font)
                    .setFontSize(12);
            document.add(product1);
            document.add(product2);

            // Total
            Paragraph total = new Paragraph("Total: $30")
                    .setFont(font)
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);
            
            // Cerrar el documento
            document.close();

            System.out.println("Boleta generada correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}