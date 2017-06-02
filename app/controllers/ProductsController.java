package controllers;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.Console;
import java.util.List;
import play.api.Logger;
import sun.rmi.runtime.Log;

/**
 * Created by AungMyatMoe on 2/6/17.
 */
public class ProductsController extends Controller {

    public Result list(){
        List<Product> products=Product.findAll();
        return ok(views.html.products.list.render(products));
    }

    public Result newProduct(){
        Form<Product> form=Form.form(Product.class);
        return ok(views.html.products.details.render(form));
    }

    public Result details(String ean){
        Product product=Product.findByEan(ean);
        if(product==null){
            return notFound(String.format("Product %s does not exist.",ean));
        }

        Form<Product> filledForm=Form.form(Product.class).fill(product);
        return ok(views.html.products.details.render(filledForm));
    }

    public Result save(){
        Form<Product> boundForm=Form.form(Product.class).bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error","Please correct the form below.");
            return badRequest(views.html.products.details.render(boundForm));
        }
        Product product=boundForm.get();
        product.save();
        flash("success",String.format("Successfully added product %s",product));
        return redirect(routes.ProductsController.list());
    }

    public Result delete(String ean){
        Product product=Product.findByEan(ean);
        if(product==null){
            return notFound(String.format("Product %s does not exit.",ean));
        }

        product.remove(product);
        return redirect(routes.ProductsController.list());
    }
}
