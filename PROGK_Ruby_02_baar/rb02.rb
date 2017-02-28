#!/usr/bin/env ruby
# Baar Alexander

class Snippet
    def initialize()
    end
end

def method_missing(method_name, *args, &block)
  case method_name
    when /^add_\S+_tag$/
      tmp = method_name[/_(.*?)_/, 1].downcase
      print "<#{tmp}>#{args[0]}</#{tmp}>"
    else
      #no match
  end
end

# Do not mofify:
# calling a given code block
def run_now( &code_to_eval )
  puts code_to_eval.call() # <div>FHJ</div><div>ITM</div><h3>SWD</h3><h3>IMS</h3>
end

# call run <code block>
run_now {
  a = Snippet.new
  
  a.add_div_tag("FHJ")   # creates and add a method add_div_tag(...) to object a 
  a.add_Div_tag("ITM")   # creates and add a method add_Div_tag(...) to object a 
  # not required, but you could also support multiple args: 
  #a.add_div_tag("SWD","IMS","IRM")
  
  a.add_h3_tag("SWD")    # creates and add a method add_h3_tag(...) to object a 
  a.add_h3_tag("IMS")    # calls existing method add_h3_tag(...) 
    
  a.html # return output of last statement
}






