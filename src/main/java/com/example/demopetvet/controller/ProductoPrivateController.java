package com.example.demopetvet.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demopetvet.entity.Categoria;
import com.example.demopetvet.entity.Producto;
import com.example.demopetvet.service.CategoriaService;
import com.example.demopetvet.service.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("producto")
public class ProductoPrivateController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\guardado";
    @Autowired
    ProductoService service;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping()
    public String productoSel(Model model) {
        List<Producto> productos = service.selectAll();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));

        productos.forEach(producto -> {
            String precioFormateado = formatoMoneda.format(producto.getPrecio());
            producto.setPrecioFormateado(precioFormateado);
        });
        model.addAttribute("productos", productos);
        return "intranet/producto";
    }

    // Ir al formulario
    @GetMapping("nuevo")
    public String productoInsertar(Model model) {
        List<Categoria> categorias = categoriaService.categoriaSel();
        model.addAttribute("categorias", categorias);

        if (categorias.isEmpty()) {
            model.addAttribute("mensajeError", "Por favor, agregue categorías antes de crear un producto.");
        }

        model.addAttribute("producto", new Producto());
        return "intranet/producto_form";
    }

    // Ir al formulario
    @GetMapping("editar/{id}")
    public String productoEditar(Model model, @PathVariable Integer id) {
        Producto producto = service.selectById(id);
        if (producto == null) {
            model.addAttribute("mensajeError", "Producto no encontrado");
            return "redirect:/producto";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.categoriaSel());
        model.addAttribute("imagenActual", producto.getImagen());
        return "intranet/producto_form";
    }

    @PostMapping("guardar")
    public String productoGuardar(
            @Valid @ModelAttribute("producto") Producto producto,
            @RequestParam(value = "img", required = false) MultipartFile file,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.categoriaSel());
            return "intranet/producto_form";
        }
        
        try {
            if (file != null && !file.isEmpty()) {
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());
                producto.setImagen(file.getOriginalFilename());
            } else if (producto.getId() != null) {
                // Recuperar la imagen existente si no se sube una nueva
                Producto productoExistente = service.selectById(producto.getId());
                producto.setImagen(productoExistente.getImagen());
            }
        } catch (Exception e) {
            model.addAttribute("msg", "Error al subir la imagen: " + e.getMessage());
            model.addAttribute("categorias", categoriaService.categoriaSel());
            return "intranet/producto_form";
        }
    
        service.insUpd(producto);
        return "redirect:/producto";
    }    

    // Eliminar
    @GetMapping("eliminar/{id}")
    public String productoEliminar(Model model, @PathVariable Integer id) {
        service.delete(id);
        return "redirect:/producto";
    }

}