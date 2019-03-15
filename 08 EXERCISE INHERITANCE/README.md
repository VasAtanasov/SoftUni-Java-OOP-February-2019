![How spec-md works](http://g.gravizo.com/svg?
  digraph specmd {
    markdown [shape=box];
    ast [shape=box];
    html [shape=box];
    markdown -> parse [weight=8];
    parse -> ast;
    ast -> print;
    edge [color=red];
    print -> html;
  }
)