#!/usr/bin/env ruby
# Baar Alexander

# add code for "exit-hook" which prints "Done" when programm exits
at_exit do
  print "Done"
end

# add code for class A which prints "Created," when created
class A
  def self.inherited(subcl)
    print "Inherited,"
  end
end

class A
  attr_accessor :blubbor

  def initialize
    print "Created,"
    @blubbor = "Just so I have a damn accessor. Intended? I don't know."
  end
end

# add code for extending class String for a new method "about"
class String
  def about()
    return "#{ObjectSpace._id2ref(self.object_id)} by FH JOANNEUM Kapfenberg"
  end
end
#### END of my CODE ####    

A.new
class B < A
end
print "2017".about+","