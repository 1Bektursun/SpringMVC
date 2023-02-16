package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping("/companies")
public class CompanyApi {
    private final CompanyService companyService;

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String getAllCompanies(Model model){
        model.addAttribute("companies",companyService.getAllCompanies());
        return "mainPage";
    }

    @GetMapping("/new")
    public String createCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "newCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("newCompany")Company company){
        companyService.save(company);
        return "redirect:/companies" ;
    }

    @DeleteMapping("{companyId}/delete")
    public String deleteCompany(@PathVariable("companyId")Long id){
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }

}
