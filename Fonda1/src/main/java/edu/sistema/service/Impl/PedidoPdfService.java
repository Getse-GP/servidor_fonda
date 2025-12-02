package edu.sistema.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import edu.sistema.dto.AtenderDto;
import edu.sistema.dto.ClienteDto;
import edu.sistema.dto.EmpleadoDto;
import edu.sistema.dto.ProductoDto;
import edu.sistema.entity.DetallePedido;
import edu.sistema.entity.Pedido;
import edu.sistema.feign.AtenderFeign;
import edu.sistema.feign.ClienteFeign;
import edu.sistema.feign.EmpleadoFeign;
import edu.sistema.service.ProductoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoPdfService {

    private final ClienteFeign clienteFeign;
    private final EmpleadoFeign empleadoFeign;
    private final ProductoService productoService;
    private final AtenderFeign atenderFeign; // ✅ Agregado

    public ByteArrayInputStream generarTicketPedido(Pedido pedido) {
        Document document = new Document(PageSize.A5);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

         // ====== ENCABEZADO ======
            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("TICKET DE PEDIDO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Datos principales
            document.add(new Paragraph("Fecha: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
            document.add(new Paragraph("Pedido Nº: " + pedido.getIdPedido()));

            // ====== CLIENTE Y MESERO ======
            ClienteDto cliente = null;
            EmpleadoDto empleado = null;

            // Cliente
            try {
                cliente = clienteFeign.obtenerPorId(pedido.getCliente());
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo obtener cliente: " + e.getMessage());
            }

            // Mesero vía AtenderFeign
            try {
                AtenderDto atencion = atenderFeign.obtenerPorPedido(pedido.getIdPedido());
                if (atencion != null && atencion.getIdEmpleado() != null) {
                    empleado = empleadoFeign.obtenerPorId(atencion.getIdEmpleado());
                }
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo obtener atención o empleado: " + e.getMessage());
            }

            document.add(new Paragraph("Cliente: " +
                    (cliente != null ? cliente.getNombreCliente() : "Desconocido")));
            document.add(new Paragraph("Mesero: " +
                    (empleado != null ? empleado.getNombre() : "Desconocido")));

            // Reserva
            if (pedido.getIdReserva() != null) {
                document.add(new Paragraph("Reserva ID: " + pedido.getIdReserva()));
            }

            document.add(new Paragraph(" "));
            document.add(new LineSeparator());
            document.add(new Paragraph("Detalles del Pedido:"));
            document.add(new Paragraph(" "));

            // ====== TABLA DE PRODUCTOS ======
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.addCell("Producto");
            table.addCell("Cantidad");
            table.addCell("Subtotal");

            for (DetallePedido detalle : pedido.getDetalles()) {
                ProductoDto producto = null;
                try {
                    producto = productoService.getProductoById(detalle.getProducto().getIdProducto());
                } catch (Exception e) {
                    System.out.println("⚠️ No se pudo obtener producto: " + e.getMessage());
                }

                table.addCell(producto != null ? producto.getNombreProducto() : "Producto #" + detalle.getProducto().getIdProducto());
                table.addCell(String.valueOf(detalle.getCantidad()));
                table.addCell("$" + detalle.getSubtotal());
            }

            document.add(table);
            document.add(new Paragraph(" "));

         // ====== TOTAL ======
            Font bold = new Font(Font.HELVETICA, 12, Font.BOLD);
            Paragraph total = new Paragraph("TOTAL: $" + pedido.getTotal(), bold);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.add(new Paragraph("¡Gracias por su compra!", new Font(Font.HELVETICA, 10, Font.ITALIC)));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
