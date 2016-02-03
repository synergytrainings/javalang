/* 
  Copyright (C) 2013 Raquel Pau and Albert Coroleu.
 
 Walkmod is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 Walkmod is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 
 You should have received a copy of the GNU Lesser General Public License
 along with Walkmod.  If not, see <http://www.gnu.org/licenses/>.*/
package org.walkmod.javalang.ast.stmt;

import java.util.List;

import org.walkmod.javalang.ast.Node;
import org.walkmod.javalang.ast.expr.Expression;
import org.walkmod.javalang.visitors.GenericVisitor;
import org.walkmod.javalang.visitors.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class SwitchEntryStmt extends Statement {

   private Expression label;

   private List<Statement> stmts;

   public SwitchEntryStmt() {
   }

   public SwitchEntryStmt(Expression label, List<Statement> stmts) {
      setLabel(label);
      setStmts(stmts);
   }

   public SwitchEntryStmt(int beginLine, int beginColumn, int endLine, int endColumn, Expression label,
         List<Statement> stmts) {
      super(beginLine, beginColumn, endLine, endColumn);
      setLabel(label);
      setStmts(stmts);
   }

   @Override
   public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
      return v.visit(this, arg);
   }

   @Override
   public <A> void accept(VoidVisitor<A> v, A arg) {
      v.visit(this, arg);
   }

   public Expression getLabel() {
      return label;
   }

   public List<Statement> getStmts() {
      return stmts;
   }

   public void setLabel(Expression label) {
      if(this.label != null){
         updateReferences(this.label);
      }
      this.label = label;
      setAsParentNodeOf(label);
   }

   public void setStmts(List<Statement> stmts) {
      this.stmts = stmts;
      setAsParentNodeOf(stmts);
   }

   @Override
   public boolean replaceChildNode(Node oldChild, Node newChild) {
      boolean updated = false;
      if (oldChild == label) {
         setLabel((Expression) newChild);
         updated = true;
      }
      if (!updated) {
         updated = replaceChildNodeInList(oldChild, newChild, stmts);
      }

      return updated;
   }
}
