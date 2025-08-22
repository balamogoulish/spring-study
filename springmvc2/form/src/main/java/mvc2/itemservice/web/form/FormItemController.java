package mvc2.itemservice.web.form;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mvc2.itemservice.domain.item.Item;
import mvc2.itemservice.domain.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "form/item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute(new Item());
        return "form/addForm";
    }

    /**
    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model){
        Item item = new Item(itemName,price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "form/item";
    }
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){
        itemRepository.save(item);
        //model.addAttribute("item", item); -> 자동 추가로 생략 가능
        return "form/item";
    }
    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "form/item";
    }
    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "form/item";
    }
    */
    @PostMapping("add")
    public String addItemV5(Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true); //status가 true이면 저장 완료 띄우기
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, Item item){
        itemRepository.update(itemId, item);
        return "redirect:/form/items/{itemId}";
    }
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
