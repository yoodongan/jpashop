package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        BookForm form = new BookForm();
        model.addAttribute("form", form);
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Valid BookForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "items/createItemForm";
        }
        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String itemList(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /* 상품 수정 */
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book book = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setIsbn(book.getIsbn());
        form.setAuthor(book.getAuthor());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        Book book = (Book) itemService.findOne(itemId);
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setIsbn(form.getIsbn());
        book.setAuthor(form.getAuthor());

        itemService.saveItem(book);
        return "redirect:/items";
    }
}
