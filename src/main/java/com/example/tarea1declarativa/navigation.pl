punto(1).
punto(2).
punto(3).
punto(4).
punto(5).
punto(6).
punto(7).
punto(8).
punto(9).
punto(10).
punto(11).
punto(12).
punto(13).
punto(14).
punto(15).
punto(16).
punto(17).
punto(18).
punto(19).
punto(20).
punto(21).
punto(22).
punto(23).
punto(24).
punto(25).
punto(26).
punto(27).
punto(28).
punto(29).
punto(30).
  
  
  

coordenada(1,-89.224267304412422, 13.703150717800634 ).

coordenada(2,-89.227109209527967, 13.703067050713086 ).

coordenada(3,-89.228896165017289, 13.702962466811764 ).

coordenada(4,-89.230747709259219, 13.702774215672081 ).

coordenada(5,-89.232706901422205, 13.702753298869473 ).

coordenada(6,-89.232728431006436, 13.703652719700306 ).

coordenada(7,-89.230618531753976, 13.703736386579315 ).

coordenada(8,-89.22885310584887, 13.703945553646497 ).

coordenada(9,-89.230446295080313, 13.705828048871826 ).

coordenada(10,-89.232491605580137, 13.706037214076886 ).

coordenada(11,-89.232427016827501, 13.707940608884595 ).

coordenada(12,-89.235462688200926, 13.708170688530808 ).

coordenada(13,-89.235634924874589, 13.706371878017634 ).

coordenada(14,-89.236000927806131, 13.702355879266182 ).

coordenada(15,-89.235828691132468, 13.703924636948157 ).

coordenada(16,-89.240565199658363, 13.708798177329566 ).

coordenada(17,-89.2408881434215, 13.705033219394245 ).

coordenada(18,-89.241017320926744, 13.702774215672081 ).

coordenada(19,-89.240328374232078, 13.710785214132702 ).

coordenada(20,-89.241943093047723, 13.708986423642306 ).

coordenada(21,-89.242222977642442, 13.706622875660329 ).

coordenada(22,-89.241900033879304, 13.70287879965719 ).

coordenada(23,-89.237529528284952, 13.710471472597236 ).

coordenada(24,-89.231996425143336, 13.710576053155627 ).

coordenada(25,-89.22680779534906, 13.707585030806259 ).

coordenada(26,-89.239015069595354, 13.714152680218579 ).

coordenada(27,-89.243817462245403, 13.710228256684607 ).
coordenada(28,-89.237789037883076, 13.708531222243993 ).
coordenada(29,-89.240845969546001, 13.706073255930214 ).
coordenada(30,-89.23893621026906, 13.704883980540865 ).
coordenada(31,-89.241691047035829, 13.710664495152757 ).
coordenada(32,-89.239495298448887, 13.710706459360988 ).
coordenada(33,-89.238861770987711, 13.710636519009769 ).
coordenada(34,-89.242950902782482, 13.709055861517729 ).
coordenada(35,-89.243951588204084, 13.709454523923759 ).
coordenada(36,-89.242182899835072, 13.705172071636358 ).
coordenada(37,-89.243514269039665, 13.703075907260443 ).



  
  
  

% Definicion de segmentos

  

segmento(1,2).

segmento(2,1).
segmento(2,3).



segmento(3,2).
segmento(3,8).
segmento(3,4).




segmento(4,3).
segmento(4,5).
segmento(4,7).



segmento(5,7).
segmento(5,4).
segmento(5,14).


segmento(6,15).
segmento(6,10).
segmento(6,7).
segmento(6,5).

segmento(7,4).
segmento(7,6).
segmento(7,9).
segmento(7,8).


segmento(8,3).
segmento(8,7).

segmento(9,7).
segmento(9,10).

segmento(10,6).
segmento(10,13).
segmento(10,11).
segmento(10,9).

segmento(11,10).
segmento(11,12).
segmento(11,24).

segmento(12,28).
segmento(12,11).
segmento(12,13).


segmento(13,15).
segmento(13,10).
segmento(13,12).

segmento(14,5).
segmento(14,18).


segmento(15,13).
segmento(15,6).
segmento(15,14).

% 20,19,29, 28
segmento(16,20).
segmento(16,19).
segmento(16,29).
segmento(16,28).

% 18, 29 , 30
segmento(17,18).
segmento(17,29).
segmento(17,30).

% 22, 14, 17
segmento(18,22).
segmento(18,14).
segmento(18,17).

% 31, 32, 16
segmento(19,31).
segmento(19,32).
segmento(19,16).

% 34, 21, 16, 31
segmento(20,34).
segmento(20,21).
segmento(20,16).
segmento(20,31).

% 36
segmento(21,36).

% (22)
segmento(22,37).
% 28, 33
segmento(23,28).
segmento(23,33).

% 11, 26
segmento(24,11).
segmento(24,26).

% 24
segmento(25,24).

% 24
segmento(26,24).

% 35
segmento(27,35).

% 16
segmento(28,16).

% 16
segmento(29,16).

% 17
segmento(30,17).

% 20
segmento(31,20).

% 19, 33
segmento(32,19).
segmento(32,33).

% 23, 32
segmento(33,23).
segmento(33,32).

% 35, 20
segmento(34,35).
segmento(34,20).

% 27, 34
segmento(35,27).
segmento(35,34).

% 22
segmento(36,22).
segmento(36,37).




% Return just one route between two points if not exists return anything
% example: route(1,2,Route).

% Regla para encontrar una ruta entre dos puntos
route(X, Y, Ruta) :-
    dfs(X, Y, [X], RutaInversa),  % Llama a la búsqueda en profundidad (DFS)
    reverse(RutaInversa, Ruta).   % Invierte la ruta encontrada

% Regla para la búsqueda en profundidad (DFS)
dfs(X, X, _, [X]).
dfs(X, Y, Visitados, [X | Ruta]) :-
    segmento(X, Z),                 % Encuentra un punto Z conectado a X
    \+ member(Z, Visitados),        % Asegura que Z no haya sido visitado previamente
    dfs(Z, Y, [Z | Visitados], Ruta).

% Predicado para comprobar si un elemento está en una lista
member(X, [X | _]).
member(X, [_ | Resto]) :-
    member(X, Resto).








