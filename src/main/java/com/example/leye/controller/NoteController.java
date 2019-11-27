/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leye.controller;

import com.example.leye.domain.Note;
import com.example.leye.domain.NoteRepository;
import com.example.leye.domain.User;
import com.example.leye.domain.UserRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Leyeseyi
 */
@Controller
public class NoteController {
    
    @Autowired
    private NoteRepository notesRepo ;
    
    @Autowired
    private UserRepository userRepo ;
    
    @RequestMapping(value="/note", method=RequestMethod.GET)
    public String getNotesPage(Model model, Principal principal){
        
        User user = userRepo.findByEmail(principal.getName());
        boolean check;
        
        Note newNote = new Note();
        
       model.addAttribute("newNote", newNote);
       model.addAttribute("profileImage", user);
       
       try{
           String name = user.getFirstname();
           String message;
           if(!(name.isEmpty())){
               message = "Welcome " + name;
               model.addAttribute("message", message);
           }
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
           
       return "notes";
    }
    
    @RequestMapping(value="/document", method=RequestMethod.GET)
    public String getAllDocuments(Model model, Principal principal){
        
        User user =userRepo.findByEmail(principal.getName());
        
        List userNotes = notesRepo.findByUserIdOrderByUpdatedAtDesc(user.getId());
        
        if(userNotes.isEmpty()){
            model.addAttribute("emptyNote", true);
            return "list";
        }
        else{
            model.addAttribute("noteNotEmpty", true);
            model.addAttribute("newList", userNotes);
            
            return "list";
        }
        
        
    }
    
     @RequestMapping(value="/create", method=RequestMethod.POST) //OR
    //@PostMapping(value="/create")
    public String createTodoRecord(@ModelAttribute(name="newNote") @Valid Note newNote, BindingResult bindingResult, Model model, Principal principal){
        //check successful save
        boolean check = false;
        
        //get the current user
        User user = userRepo.findByEmail(principal.getName());
        
        newNote.setUser(user);
        String success = "Notes added successfully";
        
        //check if form has errors
        if(bindingResult.hasErrors())
            return "notes";
        
        //submit to db
        try{
            notesRepo.save(newNote);
            check = true;
            
            if(check){
                model.addAttribute("check", true);
                model.addAttribute("success", success);
            }
            return "redirect:/note";
        }
        catch(Exception e){
            return "redirect:/note";
        }
        
    }
    
    @RequestMapping(value="/delete{id}", method=RequestMethod.GET)
    public String deleteEntry (@PathVariable(name="id") Integer id, Model model){
        
       //delete record
       
       notesRepo.deleteById(id);
      
       //retrieve list;
       model.addAttribute("list", notesRepo.findAll()); 
       
       return "redirect:/document"; //display notes page
        
    }
    @RequestMapping(value="/edit{id}", method=RequestMethod.GET)
    public String editRecord (@PathVariable(name="id") Integer id, Model model){
        //declare varaible message
        String message ="Edit Note Record  ";
        
        //retrieve record
        Optional<Note> not = notesRepo.findById(id);
        
        //check for emptiness
        if(not.isPresent()){
            
            model.addAttribute("newNote", not.get());

        }
        
        model.addAttribute("check", "false");       
        
        model.addAttribute("message", message);
        
        
       return "edit_note"; //display edit page
        
    }
    
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateRecord (@ModelAttribute(name="newNote") Note newNote, BindingResult bindingResult, Model model, Principal principal){
        
       boolean check = false;
        
        //get the current user
        User user = userRepo.findByEmail(principal.getName());
        
        newNote.setUser(user);
        String success = "Notes added successfully";
        
        //check if form has errors
        if(bindingResult.hasErrors())
            return "notes";
        
        //submit to db
        try{
            notesRepo.save(newNote);
            check = true;
            
            if(check){
                model.addAttribute("check", true);
                model.addAttribute("success", success);
            }
            return "redirect:/document";
        }
        catch(Exception e){
            return "redirect:/note";
        }
        
    }
    
    
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(){
        
        return "login";
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(){
        
        return "login";
    }
}
